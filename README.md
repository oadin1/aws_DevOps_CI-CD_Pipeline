# aws_DevOps_CI-CD_Pipeline
AWS DevOps Project: CI/CD Pipeline for a Scalable Web Application

📌 Project Overview
This project demonstrates the end-to-end deployment of a scalable web application using AWS DevOps tools. It includes:
✅ Terraform for Infrastructure as Code (IaC)
✅ Docker & Kubernetes (EKS) for containerized applications
✅ Jenkins for CI/CD automation
✅ AWS EC2, S3, IAM, CloudWatch for hosting and monitoring
✅ GitHub Actions & ELK Stack for logging & security

⚡ Architecture Diagram

        GitHub      -->  Jenkins;
        Jenkins     -->  Docker;
        Docker      -->  ECR;
        ECR         -->  EKS;
        EKS         -->  CloudWatch;
        CloudWatch  -->  Alerts;
        
This pipeline ensures that every code commit triggers an automated deployment to AWS.

🔹 Tech Stack Used
Technology	          Purpose
AWS	                  Cloud Infrastructure (EC2, S3, EKS, IAM, CloudWatch)
Terraform            	Infrastructure as Code (IaC)
Docker	              Containerization of application
Kubernetes (EKS)	    Container orchestration
Jenkins	              CI/CD Automation
GitHub Actions	      Version control and automation
ELK Stack             Logging and Monitoring

📌 1️⃣ Setup AWS Infrastructure with Terraform
🔹 Step 1: Install Terraform

# Download Terraform
      wget https://releases.hashicorp.com/terraform/1.1.3/terraform_1.1.3_linux_amd64.zip
      unzip terraform_1.1.3_linux_amd64.zip
      sudo mv terraform /usr/local/bin/
      terraform -v

🔹 Step 2: Initialize Terraform

      terraform init
      terraform apply -auto-approve
      
This creates EC2 instances, an EKS cluster, IAM roles, and an S3 bucket.

📌 2️⃣ Dockerize the Web Application
🔹 Step 1: Create a Dockerfile

      FROM python:3.9
      WORKDIR /app
      COPY . .
      RUN pip install -r requirements.txt
      CMD ["python", "app.py"]
      
🔹 Step 2: Build & Push Image to AWS ECR

      docker build -t myapp .
      aws ecr create-repository --repository-name myapp
      docker tag myapp:latest <AWS_ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/myapp
      docker push <AWS_ACCOUNT_ID>.dkr.ecr.us-east-1.amazonaws.com/myapp
      
📌 3️⃣ Deploy to Kubernetes (EKS)
🔹 Step 1: Create Kubernetes Deployment YAML

        apiVersion: apps/v1
        kind: Deployment
        metadata:
          name: myapp
        spec:
          replicas: 3
          selector:
            matchLabels:
              app: myapp
          template:
            metadata:
              labels:
                app: myapp
            spec:
              containers:
              - name: myapp
                image: <AWS_ECR_REPOSITORY>
                ports:
                - containerPort: 5000
                
🔹 Step 2: Apply Kubernetes Deployment

      kubectl apply -f deployment.yaml
      kubectl get pods
      
📌 4️⃣ Setup Jenkins for CI/CD
🔹 Step 1: Install Jenkins on AWS EC2

      sudo yum update -y
      sudo amazon-linux-extras install java-openjdk11
      wget -O /etc/yum.repos.d/jenkins.repo \
          https://pkg.jenkins.io/redhat-stable/jenkins.repo
      sudo yum install jenkins -y
      sudo systemctl enable jenkins
      sudo systemctl start jenkins
🔹 Jenkins URL: http://your-ec2-ip:8080

🔹 Step 2: Configure Jenkins Pipeline
Jenkinsfile

📌 5️⃣ Monitoring & Logging
🔹 Step 1: Setup AWS CloudWatch

    aws logs create-log-group --log-group-name myapp-log-group
    aws logs create-log-stream --log-group-name myapp-log-group --log-stream-name myapp-log-stream
    
🔹 Step 2: Integrate ELK Stack
Configure Elasticsearch, Logstash, Kibana (ELK) on EC2.
Forward logs from AWS CloudWatch to ELK.


🎯 Results & Key Takeaways
    ✅ Fully automated CI/CD pipeline
    ✅ Highly scalable web application
    ✅ AWS best practices in security, logging, monitoring
    ✅ Hands-on experience with AWS, Kubernetes & Jenkins

📌 Future Enhancements
🔹 Add Prometheus + Grafana for better monitoring.
🔹 Implement AWS Lambda for serverless automation.
🔹 Improve Security using AWS IAM, WAF, and GuardDuty.

🚀 How to Use This Project?
Clone the repo:
      git clone https://github.com/your-repo/aws-devops-project.git
      cd aws-devops-project
      
Deploy with Terraform:
    terraform init
    terraform apply -auto-approve
    
Run the CI/CD pipeline in Jenkins
Deploy to Kubernetes with kubectl apply -f deployment.yaml
Monitor with AWS CloudWatch & ELK


🙌 Contributing
    Feel free to submit pull requests or open an issue. Let's improve this AWS DevOps pipeline together! 🚀

📩 Contact & Support
    💡 Author: Omkar Deungale
    🌎 GitHub: https://github.com/oadin1
    📧 Email: omkardeungale811@gmail.com
